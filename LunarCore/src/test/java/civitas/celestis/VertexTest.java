package civitas.celestis;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.graphics.model.PredefinedModel;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class VertexTest {
    public static void main(String[] args) {
        final File file = new File("LunarCore/src/main/resources/models/bc304/BC304Render.obj");
        final Obj obj;
        try {
            obj = ObjReader.read(new FileInputStream(file));
        } catch (IOException e) {
            return;
        }

        final Model daedalus = new PredefinedModel(obj, Color.GRAY);

        System.out.println(daedalus);
    }
}
