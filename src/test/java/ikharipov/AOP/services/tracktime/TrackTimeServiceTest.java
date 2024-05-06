package ikharipov.AOP.services.tracktime;

import ikharipov.AOP.model.tracktime.MethodTimeExecution;
import ikharipov.AOP.model.tracktime.MethodType;
import ikharipov.AOP.repository.tracktime_repositories.TrackTimeRepository;
import ikharipov.AOP.services.track_time_services.TrackTimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TrackTimeService.class})
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class TrackTimeServiceTest {

    @MockBean
    private TrackTimeRepository trackTimeRepository;
    @Autowired
    private TrackTimeService trackTimeService;

    @Test
    public void testCreateAndSaveMethodTimeExecution() {
        String methodName = "testMethod";
        long executionTimeMillis = 100;
        MethodType type = MethodType.SYNC;
        trackTimeService.createAndSaveMethodTimeExecution(methodName, executionTimeMillis, type);
        assertNotNull(trackTimeRepository);
    }

    @Test
    public void testGetMethodTimeExecutionsById() {
        UUID methodId = UUID.randomUUID();
        MethodTimeExecution expectedMethodTimeExecution = new MethodTimeExecution.Builder()
                .withId(methodId)
                .withMethodName("testMethod")
                .withExecutionTimeMillis(100)
                .withCreated(new Date())
                .withMethodType(MethodType.SYNC)
                .build();
        when(trackTimeRepository.findById(methodId)).thenReturn(Optional.of(expectedMethodTimeExecution));

        MethodTimeExecution actualMethodTimeExecution = trackTimeService.getMethodTimeExecutionsById(methodId);
        assertNotNull(actualMethodTimeExecution);
        assertEquals(expectedMethodTimeExecution, actualMethodTimeExecution);
    }

    @Test
    public void testGetMaxMethodExecutionTime() {
        List<MethodTimeExecution> methodTimeExecutions = new ArrayList<>();
        methodTimeExecutions.add(new MethodTimeExecution.Builder()
                .withExecutionTimeMillis(100)
                .build());
        methodTimeExecutions.add(new MethodTimeExecution.Builder()
                .withExecutionTimeMillis(200)
                .build());
        methodTimeExecutions.add(new MethodTimeExecution.Builder()
                .withExecutionTimeMillis(300)
                .build());

        long maxExecutionTime = trackTimeService.getMaxMethodExecutionTime(methodTimeExecutions);
        assertEquals(300, maxExecutionTime);
    }

    @Test
    public void testGetMinMethodExecutionTime() {
        List<MethodTimeExecution> methodTimeExecutions = new ArrayList<>();
        methodTimeExecutions.add(new MethodTimeExecution.Builder()
                .withExecutionTimeMillis(100)
                .build());
        methodTimeExecutions.add(new MethodTimeExecution.Builder()
                .withExecutionTimeMillis(200)
                .build());
        methodTimeExecutions.add(new MethodTimeExecution.Builder()
                .withExecutionTimeMillis(300)
                .build());
        long minExecutionTime = trackTimeService.getMinMethodExecutionTime(methodTimeExecutions);
        assertEquals(100, minExecutionTime);
    }

    @Test
    public void testGetAverageMethodExecutionTime() {
        List<MethodTimeExecution> methodTimeExecutions = new ArrayList<>();
        methodTimeExecutions.add(new MethodTimeExecution.Builder()
                .withExecutionTimeMillis(100)
                .build());
        methodTimeExecutions.add(new MethodTimeExecution.Builder()
                .withExecutionTimeMillis(200)
                .build());
        methodTimeExecutions.add(new MethodTimeExecution.Builder()
                .withExecutionTimeMillis(300)
                .build());
        double averageExecutionTime = trackTimeService.getAverageMethodExecutionTime(methodTimeExecutions);
        assertEquals(200.0, averageExecutionTime);
    }
}