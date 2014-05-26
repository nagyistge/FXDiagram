package de.fxdiagram.xtext.glue.mapping

import de.fxdiagram.core.XConnection
import de.fxdiagram.core.XDiagram
import de.fxdiagram.core.XNode
import de.fxdiagram.xtext.glue.XtextDomainObjectDescriptor
import de.fxdiagram.xtext.glue.shapes.BaseConnection
import de.fxdiagram.xtext.glue.shapes.BaseNode
import java.util.List

abstract class AbstractMapping<T> {
		
	String id

	@Property Class<T> typeGuard
	
	@Property XDiagramConfig config
	
	new(String id, Class<T> typeGuard) {
		this.id = id
		this.typeGuard = typeGuard
	}
	 
	new(Class<T> typeGuard) {
		this.id = typeGuard.simpleName
		this.typeGuard = typeGuard
	}
	 
	def boolean isApplicable(Object domainObject) {
		typeGuard.isInstance(domainObject)	
	}
	
	def String getID() {
		id
	}
}

class DiagramMapping<T> extends AbstractMapping<T> {
	
	List<AbstractNodeMappingCall<?, T>> nodes = newArrayList 
	List<AbstractConnectionMappingCall<?, T>> connections = newArrayList()
	(XtextDomainObjectDescriptor<T>)=>XDiagram createDiagram = [ new XDiagram ]
	
	new(String id, Class<T> typeGuard) {
		super(id, typeGuard)
	}
	
	new(Class<T> typeGuard) {
		super(typeGuard)
	}
	
	def getNodes() { nodes }
	def getConnections() { connections }
	def getCreateDiagram() { createDiagram }
	def setCreateDiagram((XtextDomainObjectDescriptor<T>)=>XDiagram createDiagram) { this.createDiagram = createDiagram }
	
	def <U> nodeFor(NodeMapping<U> nodeMapping, (T)=>U selector) {
		nodes += new NodeMappingCall(selector, nodeMapping)
	}

	def <U> connectionFor(ConnectionMapping<U> connectionMapping, (T)=>U selector) {
		connections += new ConnectionMappingCall(selector, connectionMapping)
	}

	def <U> nodeForEach(NodeMapping<U> nodeMapping, (T)=>Iterable<? extends U> selector) {
		nodes += new MultiNodeMappingCall(selector, nodeMapping)
	}

	def <U> connectionForEach(ConnectionMapping<U> connectionMapping, (T)=>Iterable<? extends U> selector) {
		connections += new MultiConnectionMappingCall(selector, connectionMapping)
	}
}

class NodeMapping<T> extends AbstractMapping<T> {
	
	List<AbstractConnectionMappingCall<?,T>> outgoing = newArrayList
	List<AbstractConnectionMappingCall<?,T>> incoming = newArrayList()
	DiagramMappingCall<?,T> nestedDiagram = null
	(XtextDomainObjectDescriptor<T>)=>XNode createNode = [ new BaseNode(it) ]
	
	new(String id, Class<T> typeGuard) {
		super(id, typeGuard)
	}
	
	new(Class<T> typeGuard) {
		super(typeGuard)
	}
	
	def getOutgoing() { outgoing }
	def getIncoming() { incoming }
	def getNestedDiagram() { nestedDiagram } 
	def setNestedDiagram(DiagramMappingCall<?,T> nestedDiagram) { this.nestedDiagram = nestedDiagram }
	
	def getCreateNode() { createNode }
	def setCreateNode((XtextDomainObjectDescriptor<T>)=>XNode createNode) { this.createNode = createNode }
	
	def <U> outConnectionFor(ConnectionMapping<U> connectionMapping, (T)=>U selector) {
		val call = new ConnectionMappingCall(selector, connectionMapping)
		outgoing += call
		call
	}
	
	def <U> inConnectionFor(ConnectionMapping<U> connectionMapping, (T)=>U selector) {
		val call = new ConnectionMappingCall(selector, connectionMapping)
		incoming += call
		call
	}
	
	def <U> nestedDiagramFor(DiagramMapping<U> connectionMapping, (T)=>U selector) {
		nestedDiagram = new DiagramMappingCall(selector, connectionMapping)
	}
	
	def <U> outConnectionForEach(ConnectionMapping<U> connectionMapping, (T)=>Iterable<? extends U> selector) {
		val call = new MultiConnectionMappingCall(selector, connectionMapping)
		outgoing += call
		call
	}
	
	def <U> inConnectionForEach(ConnectionMapping<U> connectionMapping, (T)=>Iterable<? extends U> selector) {
		val call = new MultiConnectionMappingCall(selector, connectionMapping)
		incoming += call
		call
	}
}

class ConnectionMapping<T> extends AbstractMapping<T> {

	NodeMappingCall<?, T> source
	NodeMappingCall<?, T> target
	(XtextDomainObjectDescriptor<T>)=>XConnection createConnection = [ new BaseConnection(it) ]
	
	new(String id, Class<T> typeGuard) {
		super(id, typeGuard)
	}
	
	new(Class<T> typeGuard) {
		super(typeGuard)
	}
	
	def getCreateConnection() { createConnection }
	def setCreateConnection((XtextDomainObjectDescriptor<T>)=>XConnection createConnection) { this.createConnection = createConnection }
	def getSource() {
		source
	}
	def getTarget() {
		target
	}
	def <U> source(NodeMapping<U> nodeMapping, (T)=>U selector) {
		source = new NodeMappingCall(selector, nodeMapping)
	}

	def <U> target(NodeMapping<U> nodeMapping, (T)=>U selector) {
		target = new NodeMappingCall(selector, nodeMapping)
	}
}

