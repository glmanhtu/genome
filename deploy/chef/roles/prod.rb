name "prod"
description "Install Genome to server"
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
  	    "profile"   =>  "prod",
  	    "git"   =>  {
  	        "url"   =>  "git@github.com:glmanhtu/genome.git",
  	        "branch"    =>  "dev",
  	        "private"   => true,
  	        "databag"   => {

  	            "name"  =>  "ebi",
  	            "key"   =>  "private",
  	            "property"  =>  "private_ssh_key"
  	        }
  	    },
        "forward" => {
            "enable"    =>  true,
            "from"  =>  {
                "host"  =>  "genome-api.glmanhtu.com",
                "port"  =>  80
            },
            "to"    =>  {
                "host"  =>  "http://127.0.0.1",
                "port"  =>  8080
            }
        }
  	},
    "nodejs-deploy" => {
         "application"   =>  {
             "name"  =>  "ebi.genome"
         },
         "server"    =>  {
             "root"  =>  "/usr/local/nodejs-deploy/genome-source-root",
             "host_name" =>  "genome.glmanhtu.com",
             "port"  =>  80
         },
         "profile"   =>  "development",
         "build" =>  {
             "max_memory"    =>  750
         },
         "git"   =>  {
             "url"   =>  "git@github.com:glmanhtu/genome.git",
             "branch"    =>  "dev",
             "project_location"  =>  "client",
             "private"   => true,
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
  "recipe[maven-deploy]",
  "recipe[nodejs-deploy]"
)