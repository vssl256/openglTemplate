import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.util.Arrays;

import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20C.*;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.opengl.GL30C.glGenVertexArrays;

public class Mesh {

    private final Shader shader;

    private FloatBuffer verticesBuffer;
    private int VAO;
    private int VBO;
    private int EBO;

    private final int[] indices = {
            0, 1, 3,
            1, 2, 3
    };

    public Mesh( float[] vertices, Shader shader ) {
        this.shader = shader;

        verticesBuffer = MemoryUtil.memCallocFloat( vertices.length );
        verticesBuffer.put( 0, vertices );

        init();
    }

    private void init() {
        VAO = glGenVertexArrays();
        glBindVertexArray( VAO );

        VBO = glGenBuffers();
        glBindBuffer( GL_ARRAY_BUFFER, VBO );
        glBufferData( GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW );

        EBO = glGenBuffers();
        glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, EBO );
        glBufferData( GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW );

        glVertexAttribPointer( 0, 3, GL_FLOAT , false, 3 * Float.BYTES, 0 );
        glEnableVertexAttribArray( 0 );

        MemoryUtil.memFree( verticesBuffer );
        glPolygonMode( GL_FRONT_AND_BACK, GL_FILL );
    }

    public void draw() {
        shader.recompile();
        glUseProgram( shader.id );

        glBindVertexArray( VAO );
        glDrawElements( GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0 );
        glUseProgram( 0 );
    }
}
