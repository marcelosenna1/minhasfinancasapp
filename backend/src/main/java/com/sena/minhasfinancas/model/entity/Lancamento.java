package com.sena.minhasfinancas.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.convert.Jsr310Converters;

import com.sena.minhasfinancas.model.enums.StatusLancamento;
import com.sena.minhasfinancas.model.enums.TipoLancamento;

import lombok.Data;

@Data
@Entity
@Table(name = "lancamento", schema = "financas")
public class Lancamento {
	
	@Id 
	@Column(name = "id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "ano")
	private Integer ano;
	
	@Column(name = "mes")
	private Integer mes;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn (name = "id_usuario")
	private Usuario usario;
	
	@Column (name = "data_cadastro")
	@Convert (converter = Jsr310Converters.LocalDateToDateConverter.class)
	private LocalDate dataCadastro;
	
	@Column (name = "tipo")
	@Enumerated (value = EnumType.STRING)
	private TipoLancamento tipo;
	
	@Column (name = "status")
	@Enumerated (value = EnumType.STRING)
	private StatusLancamento status;
	
	

}
