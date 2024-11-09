package pl.szymza.app;
import pl.szymza.math.Position;
import pl.szymza.simulation.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Application {
    private final Simulation simulation;
    private final SimulationView view;
    private final OrganismPicker picker;
    private final SimulationLog logger;

    public Application() {
        view = new SimulationView();
        view.display();

        picker = new OrganismPicker();
        picker.display();

        logger = new SimulationLog();
        logger.display();

        simulation = new Simulation(
                50,
                30,
                500,
                view.getCenter()
        );
    }
    private void run() {
        while (!simulation.isFinished()) {
            if (logger.pressedLoad()) {
                simulation.load();
            }
            else if (logger.pressedSave()) {
                simulation.save();
            }

            view.draw(simulation.getState());

            if (view.wasClicked()) {
                simulation.add(
                        picker.getCurrent(),
                        view.getClicked()
                );
            }

            Packet input = view.getUserInput();
            if (input.nextTurn()) {
                simulation.run(view.getUserInput());
                logger.print(simulation.getInfo());
                simulation.clearInfo();
                view.resetUserInput();
            }

            try {
                Thread.sleep(1000/60);
            }
            catch (InterruptedException e) {
                System.out.println("Can't sleep...");
            }
        }
    }

    public static void main(String[] args) {
        var application = new Application();
        application.run();
    }
}
