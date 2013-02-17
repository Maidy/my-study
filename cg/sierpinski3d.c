#include <stdio.h>
#include <stdlib.h>

#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#include <GLUT/glut.h>

#define POINTS 10000

typedef GLfloat point[3];

void render(void);
void init(void);

int main(int argc, const char * argv[]) {
    glutInit(&argc, (char **) argv);
	glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(500, 500);
	glutInitWindowPosition(0, 0);
	glutCreateWindow("Sierpinski Gasket");
	glutDisplayFunc(render);
	init();
	glutMainLoop();
    return 0;
}

void render(void) {
    glClear(GL_COLOR_BUFFER_BIT);

    point p = { 0.0, 10.0, 10.0 };

    point tri[4] = { { 0.0, 250.0, 0.0 },
                     { 100.0, 0.0, 0.0 },
                     { -100.0, 0.0, 0.0 },
                     { 0.0, 0.0, 250.0 } };
    /*
    point tri[4] = { {   0.0,   0.0,   0.0 },
                     { 250.0, 500.0, 100.0 },
                     { 500.0, 250.0, 250.0 },
                     { 250.0, 100.0, 250.0 } };
    */
    int i, n;

    for (i = 0; i < POINTS; i++) {
      n = rand() % 4;

      p[0] = (p[0] + tri[n][0]) / 2.0;
      p[1] = (p[1] + tri[n][1]) / 2.0;
      p[2] = (p[2] + tri[n][2]) / 2.0;

      glBegin(GL_POINTS);
      glColor3f(p[0] / 250.0, p[1] / 250.0, p[2] / 250.0);
      glVertex3fv(p);
      glEnd();
    }

    glFlush();
}

void init(void) {
	glClearColor(1.0, 1.0, 1.0, 0.0);
    glColor3f(1.0, 0.0, 0.0);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(-500.0, 500.0, -500.0, 500.0, 500.0, -500.0);
    glMatrixMode(GL_MODELVIEW);
}
