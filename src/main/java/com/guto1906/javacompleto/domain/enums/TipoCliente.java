package com.guto1906.javacompleto.domain.enums;

public enum TipoCliente {
	
	PESSOAFÍSICA(1, "Pessoa Física"),
	PESSOAJURÍDICA(2, "Pessoa Jurídica");
	
	private Integer cod;
	private String descricao;
	
	private TipoCliente(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (TipoCliente c : TipoCliente.values()) {
			if (cod.equals(c.getCod())) {
				return c;
			}
		}
		throw new IllegalArgumentException("Código Inválido: " + cod);
		
	}
	
	

}
