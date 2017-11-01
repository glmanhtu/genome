name "development"
description "Install Athena BI Backend on Development Environment"
default_attributes({
	"java" => {
	    "install_flavor" => "oracle",
	    "jdk_version" => "8",
	    "oracle" => {
	      "accept_oracle_download_terms" => true
	    }
  	},
  	"maven-deploy" => {
  	    "application"   =>  {
  	        "name"  =>  "ebi.genome"
  	    },
  	    "jar"   =>  {
  	        "name"  =>  "ebi.genome.jar",
            "location"  =>  "target",
            "arg"   =>  "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
  	    },
  	    "profile"   =>  "dev",
  	    "git"   =>  {
  	        "url"   =>  "git@github.com:glmanhtu/genome.git",
  	        "branch"    =>  "dev",
  	        "databag"   => {
  	            "name"  =>  "ebi",
  	            "key"   =>  "private",
  	            "property"  =>  "private_ssh_key"
  	        }
  	    }
  	}
})

run_list(
  "recipe[java]",
  "recipe[maven]",
  "recipe[git]",
  "recipe[maven-deploy]"
)