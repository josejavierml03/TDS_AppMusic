package umu.tds.git;

import java.io.IOException;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public enum LoginGitHub {
	INSTANCE;

	public boolean verificar(String usuario, String githubPropertiesPath) {
		try {
			GitHub github = GitHubBuilder.fromPropertyFile(githubPropertiesPath).build();

			if (github.isCredentialValid()) {
				GHUser ghuser = github.getMyself();
				System.out.println("Validado! " + ghuser.getLogin());
				System.out.println("¿Login válido?: true");

				return (ghuser.getLogin().equals(usuario) && github.isCredentialValid());
			}
			return false;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}

}
