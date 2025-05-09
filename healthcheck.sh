echo "Checking if hub is ready"

while [ "$( curl -s http://hub:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
    echo "Hub not ready yet...waiting"
    sleep 1
done

echo "Hub is ready, starting tests..."
java -DrunOnGrid=yes -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* org.testng.TestNG Test.xml