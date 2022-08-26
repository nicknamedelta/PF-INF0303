import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class HangmanSuccess {
    public Game g;
    public Prompter p;
    @Given("A palavra para adivinhar e {string}")
    public void a_palavra_para_adivinhar_e(String string) {
        g = new Game(string);
    }
    @When("Eu digitei a letra correta {int} {string}")
    public void eu_digitei_a_letra_correta(Integer int1, String string) {
        g.guess(string);
    }
    @Then("Eu ganhei o jogo")
    public void eu_ganhei_o_jogo() {
        assertEquals(true, g.isSolved());
    }

}
