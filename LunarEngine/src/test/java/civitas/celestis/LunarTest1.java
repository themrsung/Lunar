package civitas.celestis;

import civitas.celestis.geometry.solid.Tetrahedron;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;

public final class LunarTest1 {
    public static void main(String[] args) {
        final Vector3 a = new Vector3(0, 0, 0);
        final Vector3 b = new Vector3(0, 0, 100).rotate(new Rotation(Vector3.POSITIVE_Y, Math.toRadians(135)));
        final Vector3 c = b.rotate(new Rotation(Vector3.POSITIVE_Y, Math.toRadians(135)));
        final Vector3 d = a.add(b).add(c).add(new Vector3(0, 100, 0));

        final Tetrahedron t = new Tetrahedron(a, b, c, d);

        System.out.println(t.signedVolume());
        System.out.println(t.volume());
    }
}
