package org.genericsystem.basic;

import org.genericsystem.kernel.Engine;

public abstract class ExampleClass {
	protected Engine engine;

	public ExampleClass(Engine engine) {
		this.engine = engine;
	}

	public ExampleClass() {
		this.engine = new Engine();
	}

	protected abstract void init();

}
