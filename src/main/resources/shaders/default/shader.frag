#version 410 core

void main() {
    vec2 pixels = vec2( gl_FragCoord );
    float color = 1 - pixels.y / 480.0f;
    gl_FragColor = vec4( color, color, color, color );
}
