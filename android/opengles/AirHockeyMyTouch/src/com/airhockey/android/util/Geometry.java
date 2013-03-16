package com.airhockey.android.util;

public class Geometry {

	public static class Point {
		public final float x, y, z;
		
		public Point(float x, float y, float z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public Point translateY(float dy) {
			return new Point(x, y + dy, z);
		}

		public Point translate(Vector v) {
			return new Point(
					x + v.x,
					y + v.y,
					z + v.z);
		}
		
		public String toString() {
			return x + "," + y + "," + z;
		}
	}
	
	public static class Circle {
		public final float radius;
		public final Point center;
		
		public Circle(Point center, float radius) {
			this.center = center;
			this.radius = radius;
		}
		
		public Circle scale(float scale) {
			return new Circle(center, radius * scale);
		}
	}
	
	public static class Cylinder {
		public final Point center;
		public final float radius;
		public final float height;
		
		public Cylinder(Point center, float radius, float height) {
			this.center = center;
			this.radius = radius;
			this.height = height;
		}
	}
	
	public static class Ray {
		
		public final Point point;
		public final Vector vector;
		
		public Ray(Point point, Vector vector) {
			this.point = point;
			this.vector = vector;
		}
	}
	
	public static class Vector {
		public final float x;
		public final float y;
		public final float z;
		
		public Vector(float x, float y, float z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		/**
		 * cross product
		 * @param v
		 */
		public Vector cross(Vector v) {
			return new Vector(
					y * v.z - z * v.y,
					z * v.x - x * v.z,
					x * v.y - y * v.x);
		}
		
		/**
		 * dot product
		 * @param v
		 * @return
		 */
		public float dot(Vector v) {
			return x * v.x + y * v.y + z * v.z;
		}
		
		public float length() {
			return (float) Math.sqrt(x * x + y * y + z * z);
		}

		public Vector scale(float s) {
			return new Vector(x * s, y * s, z * s);
		}
		
		public String toString() {
			return x + "," + y + "," + z;
		}
	}
	
	public static class Sphere {
		public final Point center;
		public final float radius;

		public Sphere(Point center, float radius) {
			this.center = center;
			this.radius = radius;
		}
	}
	
	public static class Plane {
		public final Point point;
		public final Vector normal;
		
		public Plane(Point point, Vector normal) {
			this.point = point;
			this.normal = normal;
		}
	}
	
	public static boolean intersects(Sphere sphere, Ray ray) {
		return distanceBetween(sphere.center, ray) <= sphere.radius;
	}
	
	public static Point intersectionPoint(Ray ray, Plane plane) {
		Vector v = vectorBetween(ray.point, plane.point);
		
		float scale = v.dot(plane.normal) / ray.vector.dot(plane.normal);
		return ray.point.translate(ray.vector.scale(scale));
	}
	
	public static Vector vectorBetween(Point from, Point to) {
		return new Vector(to.x - from.x, to.y - from.y, to.z - from.z);
	}
	
	public static float distanceBetween(Point pt, Ray ray) {
		Vector v1 = vectorBetween(ray.point, pt);
		Vector v2 = vectorBetween(ray.point.translate(ray.vector), pt);
		
		// 외적의 크기는 두 벡터로 구성된 면적이다.
		return v1.cross(v2).length() / ray.vector.length();
	}

	
}
