precision mediump float;

// texture data input
uniform sampler2D u_TextureUnit;

// texture coordinate
varying vec2 v_TextureCoordinates;

void main()
{
	gl_FragColor = texture2D(u_TextureUnit, v_TextureCoordinates);
}
