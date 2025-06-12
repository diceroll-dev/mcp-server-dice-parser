package dev.diceroll.mcp.roller;

import dev.diceroll.parser.Dice;
import dev.diceroll.parser.ResultTree;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class McpRollerApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpRollerApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider tools(DiceNotationService diceService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(diceService)
                .build();
    }

    @Service
    public static class DiceNotationService {

        @Tool(description = "Returns the results of a collection of dice")
        public String rollDice(String diceNotation) {
            // get the individual results of all the dice
            ResultTree resultTree = Dice.detailedRoll(diceNotation);
            // return the results a simple text string. Directly returning the ResultTree works too,
            // Claude Sonnet 4 seemed to give detailed responses when using a string
            return "Result of rolling dice: " + resultTree.getValue() + ", individual roll results were:\n\n" + Dice.debug(resultTree);
        }
    }
}
