public class Render {

    private final GlfwWindow win;
    private final Mesh mesh;

    public Render( GlfwWindow win ) {
        this.win = win;
        Shader shader = new Shader( "default" );
        mesh = new Mesh( new float[] {
                1.0f,   1.0f,   0.0f,
                1.0f,   -1.0f,  0.0f,
                -1.0f,  -1.0f,  0.0f,
                -1.0f,  1.0f,   0.0f
        }, shader );
    }

    public void render() {
        win.clear();

        mesh.draw();

        win.swapBuffers();
    }
}
