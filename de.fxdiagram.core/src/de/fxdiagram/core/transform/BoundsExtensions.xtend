package de.fxdiagram.core.transform

import javafx.geometry.Bounds
import static java.lang.Math.*
import javafx.geometry.BoundingBox
import javafx.geometry.Insets

class BoundsExtensions {

	def static operator_plus(Bounds left, Bounds right) {
		val minX = min(left.minX, right.minX)
		val minY = min(left.minY, right.minY)
		val minZ = min(left.minZ, right.minZ)
		val maxX = max(left.maxX, right.maxX)
		val maxY = max(left.maxY, right.maxY)
		val maxZ = max(left.maxZ, right.maxZ)
		new BoundingBox(minX, minY, minZ, maxX - minX, maxY-minY, maxZ - minZ)
	}
	
	def static operator_plus(Bounds it, Insets insets) {
		new BoundingBox(minX - insets.left, minY - insets.top, width + insets.left + insets.right, height + insets.top + insets.bottom)
	}
	
	def static operator_minus(Bounds it, Insets insets) {
		new BoundingBox(minX + insets.left, minY + insets.top, width - insets.left - insets.right, height - insets.top - insets.bottom)
	}
	
	def static translate(Bounds bounds, double tx, double ty, double tz) {
		new BoundingBox(bounds.minX + tx, bounds.minY + ty, bounds.minZ + tz, bounds.width, bounds.height, bounds.depth)
	}	

	def static translate(Bounds bounds, double tx, double ty) {
		new BoundingBox(bounds.minX + tx, bounds.minY + ty, bounds.width, bounds.height)
	}
	
		
}