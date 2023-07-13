import java.math.BigDecimal;
import java.time.LocalDate;

class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }
    
    //Aumentador de salários aprovado pela gerencia de RH
    public void aumentarSalario(BigDecimal percentual) {
    	
        BigDecimal aumento = salario.multiply(percentual);
        salario = salario.add(aumento);
        
    }

}