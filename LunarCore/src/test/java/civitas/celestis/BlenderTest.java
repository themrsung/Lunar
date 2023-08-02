package civitas.celestis;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.graphics.model.wavefront.WavefrontModel;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BlenderTest {
    public static void main(String[] args) {
        final File file = new File("LunarCore/src/main/resources/models/bc304/BC304Render.obj");
        final Obj obj;
        try {
            obj = ObjReader.read(new FileReader(file));
        } catch (IOException e) {
            System.out.println("Fuck you");
            return;
        }

        final Model m = new WavefrontModel(obj, 1);
    }
}
