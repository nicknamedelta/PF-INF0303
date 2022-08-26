import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class HangmanFail {
    public Game g;
    public Prompter p;
    @Given("O jogo iniciou")
    public void o_jogo_iniciou() {
        g = new Game("treehouse");
    }
    @Given("Eu digitei a palavra {string}")
    public void eu_digitei_a_palavra(String string) {
        g = new Game(string);
    }
    @When("Eu digitei a letra {int} {string}")
    public void eu_digitei_a_letra(Integer int1, String string) {
        g.guess(string);
    }
    @Then("Eu perdi o jogo")
    public void eu_perdi_o_jogo() {
        assertEquals(false, g.isSolved());
    }
}
