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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
                new Rotation(new Vector3(23, 20, 11), Math.toRadians(45)),
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

        // I'll rotate object and camera every input
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    LunarEngine.getLogger().info("Space pressed. Rotating object.");

                    lunarObject.rotate(new Rotation(Vector3.POSITIVE_Y, Math.toRadians(10)));

                    LunarEngine.getLogger().info("Rotation of object: " + lunarObject.getRotation());
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    LunarEngine.getLogger().info("Enter pressed. Rotating camera.");

                    freeCam.setAngle(freeCam.getAngle().rotate(new Rotation(Vector3.POSITIVE_Y, Math.toRadians(45))));

                    LunarEngine.getLogger().info("Rotation of camera: " + freeCam.getAngle());
                }
            }
        });

        // Add a random light source
        freeCam.getScene().addRaySource(new RandomRaySource());

        // Alright let's see what happens
    }
}
