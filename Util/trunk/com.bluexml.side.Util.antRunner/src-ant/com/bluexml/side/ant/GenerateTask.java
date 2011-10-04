package com.bluexml.side.ant;

import org.apache.tools.ant.BuildException;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.application.ui.action.utils.Generate;

public class GenerateTask extends SideComponentTask {

	private boolean generate = true;
	private boolean pack = true;
	private boolean resolveDependencies = true;

	public void setGenerate(boolean generate) {
		this.generate = generate;
	}

	public void setPack(boolean pack) {
		this.pack = pack;
	}

	public boolean isPack() {
		return pack;
	}

	public boolean isGenerate() {
		return generate;
	}

	public void setResolveDependencies(boolean resolveDependencies) {
		this.resolveDependencies = resolveDependencies;
	}

	public boolean isResolveDependencies() {
		return resolveDependencies;
	}

	@Override
	public void executeImp() throws BuildException {
		
		new ApplicationModelGenerateJobWrapper() {
			
			@Override
			public void run() throws Exception {
				Generate gen = getHeadlessGenerate();
				// override activated generation phase
				gen.setDoCompletion(pack);
				gen.setDoGenerate(generate);
				gen.setDoResolveDep(resolveDependencies);
				gen.setDoDocumentation(false);

				// get generator configuration to run
				Configuration configuration = gen.getConfiguration();
				GeneratorConfiguration elem = (GeneratorConfiguration) ApplicationUtil.getComponantConfiguration(configuration, getComponentid());

				boolean error = false;
				error = gen.launchGenerationConfiguration(error, elem);
				gen.refreshFolders();
				
				if (error) {
					throw new BuildException("Generation failed");
				}

			}
			
		}.performJob();

	}

}
