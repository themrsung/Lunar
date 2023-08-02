package civitas.celestis;

import civitas.celestis.graphics.model.wavefront.WavefrontModel;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.base.BaseObject;
import civitas.celestis.object.world.World;
import civitas.celestis.task.Task;
import civitas.celestis.ui.LFrame;
import civitas.celestis.ui.component.camera.FreeCamera;
import civitas.celestis.world.RealisticWorld;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public final class LunarTest1 {
    public static void main(String[] args) {
        // Start engine
        LunarEngine.start();

        // Create test world
        final World world = new RealisticWorld(UUID.randomUUID(), "Test");

        // Add world
        LunarEngine.getWorldManager().addWorld(world);

        // Load model
        final File file = new File("LunarCore/src/main/resources/models/bc304/BC304Render.obj");
        final Obj obj;
        try {
            obj = ObjReader.read(new FileReader(file));
        } catch (IOException e) {
            System.out.println("Fuck you");
            return;
        }

        // Create test object
        final BaseObject lunarObject = new TestObject(
                UUID.randomUUID(),
                Vector3.ZERO,
                Rotation.NO_ROTATION,
                new TestProfile(),
                new WavefrontModel(obj, 1)
        );

        // Add object to world
        world.addObject(lunarObject);

        // Create UI
        final LFrame frame = new LFrame("Test");
        final FreeCamera freeCam = new FreeCamera(world);

        // Setup UI
        frame.add(freeCam);
        frame.setSize(1920, 1080);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                LunarEngine.stop();
            }
        });

        // Setup repainting task
        LunarEngine.getScheduler().register(new Task() {
            @Override
            public void execute(long delta) {
                freeCam.renderAndRepaint();
            }

            @Override
            public long interval() {
                return 10;
            }
        });

        // Show UI
        frame.setVisible(true);

        // Position object
        lunarObject.setLocation(new Vector3(0, 0, 500));

        // Add rotation rate to object
        lunarObject.setRotationRate(new Rotation(new Vector3(10, 20, 30), Math.toRadians(1)));

        // Add a random light source
        freeCam.getScene().addRaySource(new RandomRaySource());

        // Position and orient camera
        freeCam.setOrigin(new Vector3(0, 100, 0));
        freeCam.setAngle(freeCam.getAngle().rotate(new Rotation(Vector3.POSITIVE_X, Math.toRadians(-15))));

        // Alright let's see what happens
        LunarEngine.getLogger().info("Polygon count: " + lunarObject.getModel().vertices(Vector3.ZERO, Rotation.NO_ROTATION).size());
    }
}
