package pl.lanuda.kret.limb.benchmark;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.EventObject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class BenchmarkTaskTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private BenchmarkTask benchmarkTask;

    @Before
    public void setUp() throws Exception {
        benchmarkTask = new BenchmarkTask(new BenchmarkInput(100, 100), null, null);
        benchmarkTask.setSeed(42);
    }

    @Test
    public void testConstructorNullInputValidation() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("input cannot be null");
        benchmarkTask = new BenchmarkTask(null, null, null);
    }

    @Test
    public void testEmptyIntersectionResult() {
        BenchmarkResult result = benchmarkTask.call();
        assertThat(result.getIntersection(), is(empty()));
    }

    @Test
    public void testNonEmptyIntersectionResult() {
        benchmarkTask.setSeed(756251);
        BenchmarkResult result = benchmarkTask.call();
        assertThat(result.getIntersection(), hasSize(1));
        assertThat(result.getIntersection().get(0), is(1204578400));
    }

    @Test
    public void testPassingNullSuccessHandler() {
        assertThat(benchmarkTask.getOnSucceeded(), is(nullValue()));
    }

    @Test
    public void testPassingNonNullSuccessHandler() {
        benchmarkTask = new BenchmarkTask(new BenchmarkInput(100, 100), EventObject::toString, null);
        assertThat(benchmarkTask.getOnSucceeded(), is(notNullValue()));
    }

    @Test
    public void testPassingNullFailureHandler() {
        assertThat(benchmarkTask.getOnFailed(), is(nullValue()));
    }

    @Test
    public void testPassingNonNullFailureHandler() {
        benchmarkTask = new BenchmarkTask(new BenchmarkInput(100, 100), null, EventObject::toString);
        assertThat(benchmarkTask.getOnFailed(), is(notNullValue()));
    }
}
