require 'java/task'
Java::BuildTask.new(ROOT_PATH) do |t|
  t.source = [File.join(ROOT_PATH, 'java')]
  t.test_source = [File.join(ROOT_PATH, 'test', 'java')]
end

task :default => :test
