#include <stdio.h>
#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#include <GLUT/glut.h>

void Render(void);
void Initialise();

int main(int argc, const char * argv[])
{

    glutInit(&argc, (char **) argv);
	glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(600,600);
	glutInitWindowPosition(0,0);
	glutCreateWindow("GlutApp");
	glutDisplayFunc(Render);
	Initialise();
	glutMainLoop();
    
    // insert code here...
    printf("Hello, World!\n");
    return 0;
}

// ---- Render Function ----
void Render(void)
{
    glClear(GL_COLOR_BUFFER_BIT);
    
    glColor3f(1.0f, 0.0f, 0.0f);
	glBegin(GL_POLYGON);
    glVertex2f(-0.5, -0.5);
    glVertex2f(-0.5, 0.5);
    glVertex2f(0.5, 0.5);
    glVertex2f(0.5, -0.5);
	glEnd();
    
	glFlush();
}

// ---- Initialise Function ----
void Initialise()
{
	glClearColor (0.0, 0.0, 1.0, 0.0);
}
