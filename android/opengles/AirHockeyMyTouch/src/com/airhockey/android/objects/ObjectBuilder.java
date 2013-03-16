package com.airhockey.android.objects;

import java.util.ArrayList;
import java.util.List;

import static android.opengl.GLES20.*;

import com.airhockey.android.util.Geometry;
import com.airhockey.android.util.Geometry.Circle;
import com.airhockey.android.util.Geometry.Cylinder;

public class ObjectBuilder {

	static interface DrawCommand {
		void draw();
	}
	
	static class GeneratedData {
		final float[] vertexData;
		final List<DrawCommand> drawList;

		public GeneratedData(float[] vertexData, List<DrawCommand> drawList) {
			this.vertexData = vertexData;
			this.drawList = drawList;
		}
	}
	
	private static final int FLOATS_PER_VERTEX = 3;
	
	private final float[] vertexData;
	private int offset = 0;
	private final List<DrawCommand> drawList = new ArrayList<DrawCommand>();
	
	private ObjectBuilder(int sizeInVertices) {
		vertexData = new float[sizeInVertices * FLOATS_PER_VERTEX];
	}
	
	/**
	 * TRIANGLE_FAN 으로 원을 그리는데 필요한 vertex 수 
	 * @param numPoints 원을 표현하는 점 수
	 * @return
	 */
	private static int sizeOfCircleInVertices(int numPoints) {
		return 1 + (numPoints + 1);
	}
	
	/**
	 * TRIANGLE_STRIP으로 뚜껑이 없는 원통을 그리는데 필요한 vertex 수
	 * @param numPoints 원을 표현하는 점 수
	 * @return
	 */
	private static int sizeOfOpenCylinderInVertices(int numPoints) {
		return (numPoints + 1) * 2;
	}
	
	static GeneratedData createPuck(Geometry.Cylinder puck, int numPoints) {
		int size = sizeOfCircleInVertices(numPoints) +
				sizeOfOpenCylinderInVertices(numPoints);
		
		ObjectBuilder builder = new ObjectBuilder(size);
		
		Geometry.Circle puckTop = new Geometry.Circle(
				puck.center.translateY(puck.height / 2f), puck.radius);
		
		builder.appendCircle(puckTop, numPoints);
		builder.appendOpenCylinder(puck, numPoints);
		
		return builder.build();
	}
	
	static GeneratedData createMallet(Geometry.Point center, float radius,
			float height, int numPoints) {
		
		int size = (sizeOfCircleInVertices(numPoints) +
				sizeOfOpenCylinderInVertices(numPoints)) * 2;
		
		ObjectBuilder builder = new ObjectBuilder(size);
		
		float baseHeight = height * 0.25f;
		
		Geometry.Circle baseCircle = new Geometry.Circle(
				center.translateY(-baseHeight), radius);
		
		Geometry.Cylinder baseCylinder = new Geometry.Cylinder(
				baseCircle.center.translateY(-baseHeight * 0.5f), radius, baseHeight);
		
		builder.appendCircle(baseCircle, numPoints);
		builder.appendOpenCylinder(baseCylinder, numPoints);
		
		float handleHeight = height * 0.75f;
		float handleRadius = radius / 3f;
		
		Geometry.Circle handleCircle = new Geometry.Circle(
				center.translateY(height / 2f), handleRadius);
		
		Geometry.Cylinder handleCylinder = new Geometry.Cylinder(
				handleCircle.center.translateY(-handleHeight * 0.5f), handleRadius, handleHeight);
		
		builder.appendCircle(handleCircle, numPoints);
		builder.appendOpenCylinder(handleCylinder, numPoints);
		
		return builder.build();
	}

	private GeneratedData build() {
		return new GeneratedData(vertexData, drawList);
	}
	
	private void appendCircle(Circle circle, int numPoints) {
		
		final int startVertex = offset / FLOATS_PER_VERTEX; // 시작 위치
		final int numVertices = sizeOfCircleInVertices(numPoints);
		
		vertexData[offset++] = circle.center.x;
		vertexData[offset++] = circle.center.y;
		vertexData[offset++] = circle.center.z;
		
		for (int i = 0; i <= numPoints; i++) {
			float a = (i / (float) numPoints) * (float)Math.PI * 2f;
			vertexData[offset++] = circle.center.x + (float) (circle.radius * Math.cos(a));
			vertexData[offset++] = circle.center.y;
			vertexData[offset++] = circle.center.z + (float) (circle.radius * Math.sin(a));
		}
		
		drawList.add(new DrawCommand() {
			@Override
			public void draw() {
				glDrawArrays(GL_TRIANGLE_FAN, startVertex, numVertices);
			}
		});
	}

	private void appendOpenCylinder(Cylinder cylinder, int numPoints) {
		
		final int startVertex = offset / FLOATS_PER_VERTEX; // 시작 위치
		final int numVertices = sizeOfOpenCylinderInVertices(numPoints);
		
		float yStart = cylinder.center.y + cylinder.height * 0.5f;
		float yEnd = cylinder.center.y - cylinder.height * 0.5f;
		
		for (int i = 0; i <= numPoints; i++) {
			
			float a = (i / (float) numPoints) * (float)Math.PI * 2f;
			float x = cylinder.center.x + (float) (cylinder.radius * Math.cos(a));
			float z = cylinder.center.z + (float) (cylinder.radius * Math.sin(a));
			
			vertexData[offset++] = x;
			vertexData[offset++] = yStart;
			vertexData[offset++] = z;
			
			vertexData[offset++] = x;
			vertexData[offset++] = yEnd;
			vertexData[offset++] = z;
		}
		
		drawList.add(new DrawCommand() {
			@Override
			public void draw() {
				glDrawArrays(GL_TRIANGLE_STRIP, startVertex, numVertices);
			}
		});
		
	}
	
}
