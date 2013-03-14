package com.airhockey.android.objects;

import java.util.List;

import com.airhockey.android.data.VertexArray;
import com.airhockey.android.programs.ColorShaderProgram;
import com.airhockey.android.util.Geometry;

public class Puck {
	
	private static final int POSITION_COMPONENT_COUNT = 3;
	
	public final float radius, height;
	
	private final VertexArray vertexArray;
	private final List<ObjectBuilder.DrawCommand> drawList;
	
	public Puck(float radius, float height, int numPointsAroundPack) {
		
		ObjectBuilder.GeneratedData generatedData = ObjectBuilder.createPuck(
				new Geometry.Cylinder(new Geometry.Point(0f, 0f, 0f), radius, height),
				numPointsAroundPack);
		
		this.radius = radius;
		this.height = height;
		
		vertexArray = new VertexArray(generatedData.vertexData);
		drawList = generatedData.drawList;
	}
	
	public void bindData(ColorShaderProgram colorProgram) {
		vertexArray.setVertexAttributePointer(
				0,
				colorProgram.getPositionAttributeLocation(),
				POSITION_COMPONENT_COUNT, 0);
	}
	
	public void draw() {
		for (ObjectBuilder.DrawCommand command : drawList)
			command.draw();
	}
	
}
