import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        List<ExemploCallable> tarefas = Arrays.asList(
                new ExemploCallable(10000),
                new ExemploCallable(5000),
                new ExemploCallable(7000),
                new ExemploCallable(3000),
                new ExemploCallable(3000),
                new ExemploCallable(8000),
                new ExemploCallable(2000),
                new ExemploCallable(5000),
                new ExemploCallable(4000),
                new ExemploCallable(10000));

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(threadPool);

        //executa as tarefas
        System.out.println("Tarefas iniciadas, aguardando conclusão");
        for (ExemploCallable tarefa : tarefas) {
            completionService.submit(tarefa);
        }
        System.out.println("Tarefa em andamento");

        //aguarda e imprime o retorno de cada uma
        System.out.println("PID: | Ciclos | Status ");
        for (int i = 0; i < tarefas.size(); i++) {
            try {
                System.out.println((i) + " | " + completionService.take().get() + " | Concluída");
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        threadPool.shutdown();
    }
}