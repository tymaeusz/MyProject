import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		// Alguns caras legais pra me ajudar no parse de data e moeda

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		// Lista de usuários

		List<Funcionario> funcionarios = new ArrayList<>();

		// Adicionando Funcionarios na lista

		System.out.println(
				"<----------Adicionando funcionarios na lista na ordem oferecida pela tabela---------->" + "\n" + "\n");

		funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"),
				"Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"),
				"Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"),
				"Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"),
				"Diretor"));
		funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"),
				"Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"),
				"Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"),
				"Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"),
				"Gerente"));
		funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"),
				"Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"),
				"Gerente"));

		// Adeus João foi bom trabalhar com você

		funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
		System.out.println("<----------Enviando João ao RH---------->" + "\n" + "\n");

		// Printando a lista de Funcionarios

		try {

			System.out.println("<----------Lista de funcionarios, antes dos 10% de aumento---------->" + "\n");
			for (Funcionario funcionario : funcionarios) {
				System.out.println("<----------Funcionario---------->");
				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
				System.out.println("Salário: " + currencyFormat.format(funcionario.getSalario()));
				System.out.println("Função: " + funcionario.getFuncao());
				System.out.println("<----------Fim---------->" + "\n");
			}
			System.out.println("<----------Fim da lista---------->" + "\n" + "\n");
		} catch (Exception e) {

			System.out.println("Ocorrência de Exception, checar--->" + e);
			e.printStackTrace();
			System.out.println("Avançando para próxima iteração");
			System.out.println("<----------Fim---------->" + "\n");

		}

		// João se foi, mas à que custo?

		try {

			System.out.println("<----------Aplicando aumento de 10%---------->" + "\n" + "\n");

			for (Funcionario funcionario : funcionarios) {
				funcionario.aumentarSalario(new BigDecimal("0.10"));
			}

		} catch (Exception e) {

			System.out.println("Ocorrência de Exception, checar--->" + e);
			e.printStackTrace();
			System.out.println("<----------Fim---------->" + "\n");

		}

		// Agrupando Funcionarios por função

		System.out.println("<----------Agrupando Funcionarios por função---------->" + "\n" + "\n");
		Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
				.collect(Collectors.groupingBy(Funcionario::getFuncao));

		// Printando os grupos de acordo com função, salário com 10% de aumento

		try {

			System.out.println("<----------Map de funcionarios agrupados por função---------->" + "\n");
			for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
				System.out.println("<----------" + "Função: " + entry.getKey() + "---------->");

				System.out.println("<----------" + "Funcionarios" + "---------->");

				for (Funcionario funcionario : entry.getValue()) {
					System.out.println("Nome: " + funcionario.getNome());
					System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
					System.out.println("Salário: " + currencyFormat.format(funcionario.getSalario()));

				}

				System.out.println("<----------Fim---------->" + "\n");
			}

			System.out.println("<----------Fim da lista---------->" + "\n" + "\n");
		} catch (Exception e) {

			System.out.println("Ocorrência de Exception, checar--->" + e);
			e.printStackTrace();
			System.out.println("Avançando para próxima iteração");
			System.out.println("<----------Fim---------->" + "\n");

		}

		// Printando a lista de Funcionarios cujos aniversariantes sejam do mês 10 ou 12

		try {

			System.out.println("<----------Lista dos aniversariantes meses 10 e 12---------->" + "\n");

			for (Funcionario funcionario : funcionarios) {
				if (funcionario.getDataNascimento().getMonthValue() == 10
						|| funcionario.getDataNascimento().getMonthValue() == 12) {

					System.out.println("<----------Funcionario aniversariante---------->");
					System.out.println("Nome: " + funcionario.getNome());
					System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
					System.out.println("Salário: " + currencyFormat.format(funcionario.getSalario()));
					System.out.println("Função: " + funcionario.getFuncao());
					System.out.println("<----------Fim---------->" + "\n");
				}
			}
			System.out.println("<----------Fim da lista---------->" + "\n" + "\n");

		} catch (Exception e) {

			System.out.println("Ocorrência de Exception, checar--->" + e);
			e.printStackTrace();
			System.out.println("Avançando para próxima iteração");
			System.out.println("<----------Fim---------->" + "\n");

		}

		// Aplicando organização com base em uma comparação reversa das idades
		// calculadas através do período entre hoje e a data de nascimento

		System.out.println(
				"<----------Organizando lista de funcionarios por idade decrescente---------->" + "\n" + "\n");
		funcionarios.sort(Comparator
				.comparing(funcionario -> Period
						.between(((Funcionario) funcionario).getDataNascimento(), LocalDate.now()).getYears())
				.reversed());

		// Printando a lista de Funcionarios por idade decrescente

		try {

			System.out.println("<----------Lista de funcionarios por idade decrescente---------->" + "\n");

			for (Funcionario funcionario : funcionarios) {

				System.out.println("<----------Funcionario---------->");
				int age = Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("Idade: " + age);

				System.out.println("<----------Fim---------->" + "\n");
			}
			System.out.println("<----------Fim da lista---------->" + "\n" + "\n");

		} catch (Exception e) {

			System.out.println("Ocorrência de Exception, checar--->" + e);
			e.printStackTrace();
			System.out.println("Avançando para próxima iteração");
			System.out.println("<----------Fim---------->" + "\n");

		}

		// Aplicando organização em ordem alfabética do nome

		System.out.println(
				"<----------Organizando lista de funcionarios por ordem alfabética do nome---------->" + "\n" + "\n");
		Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));

		// Printando a lista de Funcionarios por ordem alfabética

		try {

			System.out.println("<----------Lista de funcionarios por ordem alfabética---------->" + "\n");

			for (Funcionario funcionario : funcionarios) {
				System.out.println("<----------Funcionario A~Z---------->");
				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
				System.out.println("Salário: " + currencyFormat.format(funcionario.getSalario()));
				System.out.println("Função: " + funcionario.getFuncao());
				System.out.println("<----------Fim---------->" + "\n");
			}

			System.out.println("<----------Fim da lista---------->" + "\n" + "\n");

		} catch (Exception e) {

			System.out.println("Ocorrência de Exception, checar--->" + e);
			e.printStackTrace();
			System.out.println("Avançando para próxima iteração");
			System.out.println("<----------Fim---------->" + "\n");

		}

		// Somando o salário do pessoal

		BigDecimal totalSalario = BigDecimal.ZERO;
		try {

			System.out.println("<----------Somando salários---------->" + "\n");
			for (Funcionario funcionario : funcionarios) {
				totalSalario = totalSalario.add(funcionario.getSalario());
			}
		} catch (Exception e) {

			System.out.println("Ocorrência de Exception, checar--->" + e);
			e.printStackTrace();

		}

		// Printando a soma do salário do pessoal

		System.out.println("Total dos Salários: " + currencyFormat.format(totalSalario) + "\n" + "\n");

		// Organizando Funcionarios pelo maior salário

		System.out.println("<----------Organizando lista de funcionarios pelo maior salário bruto---------->" + "\n" + "\n");
		Collections.sort(funcionarios, Comparator.comparing(Funcionario::getSalario).reversed());

		// Calculando quantidade de salários minímos para cada Funcionario

		try {
			BigDecimal salarioMinimoReal = new BigDecimal("1212.00");
			System.out.println("<----------Lista de funcionarios pela quantidade de salário minímo---------->" + "\n");

			for (Funcionario funcionario : funcionarios) {

				System.out.println("<----------Funcionario---------->");
				BigDecimal salarioMinimo = funcionario.getSalario().divide(salarioMinimoReal, 2, BigDecimal.ROUND_DOWN);
				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("Salário: " + currencyFormat.format(funcionario.getSalario()));
				System.out.println("Quantidade de salário minímo: " + salarioMinimo);
				System.out.println("<----------Fim---------->" + "\n");
			}

			System.out.println("<----------Fim da lista---------->" + "\n" + "\n");

		} catch (Exception e) {

			System.out.println("Ocorrência de Exception, checar--->" + e);
			e.printStackTrace();
			System.out.println("Avançando para próxima iteração");
			System.out.println("<----------Fim---------->" + "\n");

		}

	}
}