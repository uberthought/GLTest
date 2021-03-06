precision mediump float;
varying vec2 vTextureCoord;
uniform sampler2D sTexture;

uniform vec2 offset[9];

void main(void)
{
    vec4 sample[9];

    for (int i = 0; i < 9; i++)
    {
        sample[i] = texture2D(sTexture, vTextureCoord + offset[i]);
    }

//    -1 -2 -1       1 0 -1 
// H = 0  0  0   V = 2 0 -2
//     1  2  1       1 0 -1
//
// result = sqrt(H^2 + V^2)

    vec4 horizEdge = sample[2] + (2.0*sample[5]) + sample[8] -
                     (sample[0] + (2.0*sample[3]) + sample[6]);

    vec4 vertEdge = sample[0] + (2.0*sample[1]) + sample[2] -
                    (sample[6] + (2.0*sample[7]) + sample[8]);

    gl_FragColor.rgb = sqrt((horizEdge.rgb * horizEdge.rgb) + 
                            (vertEdge.rgb * vertEdge.rgb));
    gl_FragColor.a = 1.0;
}

