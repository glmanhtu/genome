current_dir = File.dirname(__FILE__)
log_level                :info
log_location             STDOUT
node_name                "glmanhtu"

client_key               "#{current_dir}/client.pem"
chef_server_url          "https://api.chef.io/organizations/athena_server"

cookbook_path    ["cookbooks", "site-cookbooks"]
node_path        "nodes"
role_path        "roles"
environment_path "environments"
data_bag_path    "data_bags"

knife[:berkshelf_path] = "cookbooks"
Chef::Config[:ssl_verify_mode] = :verify_peer if defined? ::Chef