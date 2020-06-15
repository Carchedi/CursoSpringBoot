package com.carchedi.cursoSpringBoot.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	private int cod;
	private String descricao;

	private EstadoPagamento(int cod, String desc) {
		this.cod = cod;
		this.descricao = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (EstadoPagamento e: EstadoPagamento.values()) {
			if (cod.equals(e.getCod())) {
				return e;
			}
		}

		throw new IllegalArgumentException("Estado do pagamento inválido: "+cod);
	}
}
