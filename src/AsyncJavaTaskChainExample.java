import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class AsyncJavaTaskChainExample {

    public static void main(String[] args) {

        CompletableFuture.supplyAsync(() -> supplyRandomNumber())
                .thenApply(number -> addOne(number))
                .thenApply(number -> addTwo(number))
                .thenApply(number -> addThree(number))
                .exceptionally(exception -> handleException(exception))
                .thenAccept(result -> printResult(result));

        Logger.getAnonymousLogger().info("main() executed on thread: " + Thread.currentThread().getName());

        while (true){} // just to keep the program running
    }

    private static int supplyRandomNumber() {
        Logger.getAnonymousLogger().info("supplyRandomNumber() executed on thread: " + Thread.currentThread().getName());
         int randomNumber = new Random().nextInt(10);
        Logger.getAnonymousLogger().info("supplyRandomNumber : "+randomNumber);
        return  randomNumber;
    }

    private static int addOne(int number) {
        Logger.getAnonymousLogger().info("addOne() executed on thread: " + Thread.currentThread().getName());

        return number + 1;
    }

    private static int addTwo(int number) {
        Logger.getAnonymousLogger().info("addTwo() executed on thread: " + Thread.currentThread().getName());

        return number + 2;
    }

    private static int addThree(int number) {
        Logger.getAnonymousLogger().info("addThree() executed on thread: " + Thread.currentThread().getName());

        return number + 3;
    }
    private static void printResult(int result) {
        Logger.getAnonymousLogger().info("printResult() executed on thread: " + Thread.currentThread().getName());

        Logger.getAnonymousLogger().info("Final Result: " + result);
    }

    private static int handleException(Throwable exception) {
        Logger.getAnonymousLogger().info("handleException() executed on thread: " + Thread.currentThread().getName());

        Logger.getAnonymousLogger().info("Exception occurred: " + exception.getMessage());
        return 0;
    }
}
