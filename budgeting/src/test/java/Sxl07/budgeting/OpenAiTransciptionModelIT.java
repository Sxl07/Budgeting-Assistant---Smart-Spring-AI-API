package Sxl07.budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiTransciptionModelIT {
    @Autowired
    OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;
    
    
    @ParameterizedTest
    @CsvSource({
        "HomemBR.mp3, dólares ",
        "MulherBR.mp3, café "
    })
    
    public void should_containExpectedKeywords_when_audioFilesAreProcessed(String filename,String ExpectedKeywords){ 
        var recording = new FileSystemResource("src/test/resources/" + filename);

        var response = openAiAudioTranscriptionModel.call(recording);

        assertThat(response).isNotEmpty().contains(ExpectedKeywords);
        System.out.println(response);

    }
}
