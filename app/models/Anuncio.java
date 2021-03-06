package models;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.common.base.Objects;

// Entidade que representa um Livro
// Referenciar a uma tabela
@Entity(name = "Anuncio")
public class Anuncio implements Comparable<Anuncio>{

	// Todo Id tem que ter o GeneratedValue a não ser que ele seja setado
	// Usar Id sempre Long
	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Anunciante anunciante;

    @Column
	private String nome;
	@Column
	private String descricao;
	
	@Column(name = "date")
	@Temporal(value = TemporalType.DATE)
	private Date date;
	
	@Transient
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Column
	private String interesse;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="stylesLike")
	private List<Style> stylesLike;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="stylesNotLike")
	private List<Style> stylesNotLike;
	
	@Column
	private String palavrachave;
	// Construtor vazio para o Hibernate criar os objetos
	public Anuncio() {
		this.date = new Date();
	}
    
    public Anuncio(String nome, Anunciante anunciante,String descricao,List<Style> stylesLike,
    		List<Style> stylesNotLike , String interesse) {
        
    	this.anunciante	 = anunciante;
        this.nome = nome;
        this.descricao = descricao;
        this.stylesLike = stylesLike;
        this.stylesNotLike = stylesLike;
        this.date = new Date();
    }


    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDateFormat() { 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	public Long getId() {
		return id;
	}
    
    public void setId(long id) {
        this.id = id;
    }
    

    public String getNome() {
		return nome;
	}

    public void setNome(String titulo) throws Exception {
		if (titulo.trim().equals("") || titulo == null) {
			throw new Exception("Insira um titulo ao anuncio");
		}
		this.nome = titulo;
	}
    
    public Anunciante getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

	public String getPalavrachave() {
		return palavrachave;
	}

	public void setPalavraChave(String palavraChave) throws Exception {
		if(palavraChave.trim().equals("") || palavraChave == null){
			throw new Exception("Insira uma palavra chave ao anuncio para que voce um dia possa excluir ele.");
		}
		this.palavrachave = palavraChave;
	}

	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) throws Exception {
		if (descricao.trim().equals("") || descricao == null) {
			throw new Exception("Insira um descriçao ao anuncio.");
		}
		if (descricao.length() < 5) {
			throw new Exception(
					"Insira uma descrisao de no minimo 5 caracters.");
		}
		this.descricao = descricao;
	}
	
	public String getInteresse() {
		return interesse;
	}


	public void setInteresse(String interesse) throws Exception {
		if(interesse.trim().equals("")){
			throw new Exception("Insira onde voce quer tocar.");
		}
		this.interesse = interesse;
	}


	public List<Style> getStylesLike() {
		return stylesLike;
	}


	public void setStylesLike(List<Style> stylesLike) {
		this.stylesLike = stylesLike;
	}


	public List<Style> getStylesNotLike() {
		return stylesNotLike;
	}


	public void setStylesNotLike(List<Style> stylesNotLike) {
		this.stylesNotLike = stylesNotLike;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Anuncio)) {
			return false;
		}
		Anuncio outroAnuncio = (Anuncio) obj;
		return Objects.equal(outroAnuncio.getNome(), this.getNome());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.getNome());
	}

	@Override
	public int compareTo(Anuncio o) {
		return this.date.compareTo(o.getDate()) * (-1);
	}

}
