name "production"
description "Install Athena BI Backend Service"
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
  	        "name"  =>  "athena.bi"
  	    },
  	    "jar"   =>  {
  	        "name"  =>  "services.jar",
            "location"  =>  "services/target",
            "arg"   =>  "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
  	    },
  	    "profile"   =>  "production",
  	    "git"   =>  {
  	        "url"   =>  "git@gitlab.com:game_athena/backend/athena_bi.git",
  	        "branch"    =>  "development",
  	        "private"   => true,
            "databag"   =>  {
                "name"  =>  "databag",
                "key"   =>  "private",
                "property"  =>  "private_ssh_key"
            }
  	    },
  	    "forward" => {
  	        "enable"    =>  true,
  	        "from"  =>  {
  	            "host"  =>  "apibi.athena.studio",
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
            "name"  =>  "athena.bi"
        },
        "server"    =>  {
            "root"  =>  "/usr/local/nodejs-deploy/athena-source-root",
            "host_name" =>  "bi.athena.studio",
            "port"  =>  80,
            "https" => true
        },
        "profile"   =>  "production",
        "build" =>  {
            "max_memory"    =>  750
        },
        "git"   =>  {
            "url"   =>  "git@gitlab.com:game_athena/backend/athena_bi.git",
            "branch"    =>  "development",
            "project_location"  =>  "client",
            "private"   => true,
            "databag"   =>  {
                "name"  =>  "databag",
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