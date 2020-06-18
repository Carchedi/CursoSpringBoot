package com.carchedi.cursoSpringBoot.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carchedi.cursoSpringBoot.domain.ItemPedido;
import com.carchedi.cursoSpringBoot.domain.PagamentoComBoleto;
import com.carchedi.cursoSpringBoot.domain.Pedido;
import com.carchedi.cursoSpringBoot.domain.enums.EstadoPagamento;
import com.carchedi.cursoSpringBoot.repositories.ItemPedidoRepository;
import com.carchedi.cursoSpringBoot.repositories.PagamentoRepository;
import com.carchedi.cursoSpringBoot.repositories.PedidoRepository;
import com.carchedi.cursoSpringBoot.repositories.ProdutoRepository;
import com.carchedi.cursoSpringBoot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService bolService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired 
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id +", Tipo: "+
																  Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			bolService.preenchetPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens()); 
		return obj;
	}
}
