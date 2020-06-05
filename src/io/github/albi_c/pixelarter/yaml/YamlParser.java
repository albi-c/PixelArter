package io.github.albi_c.pixelarter.yaml;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlParser {
	public Yaml yaml = new Yaml();
	
	public Map<String, Object> readYaml(String path) {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
		return this.yaml.load(inputStream);
	}
}
