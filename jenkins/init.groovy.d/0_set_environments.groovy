import groovy.json.JsonSlurper


def textJson = new File("/var/jenkins_home/init.groovy.d/credentials.json").text
def jsonSlurper = new JsonSlurper()

def creds = jsonSlurper.parseText(textJson)


System.setProperty("AWS_ACCESS_KEY", creds.AWS_ACCESS_KEY);
System.setProperty("AWS_ACCESS_SECRET", creds.AWS_ACCESS_SECRET);

System.setProperty("GIT_USER_NAME", creds.GIT_USER_NAME);
System.setProperty("GIT_USER_PASSWORD", creds.GIT_USER_PASSWORD);

System.setProperty("DOCKERHUB_USER_NAME", creds.DOCKERHUB_USER_NAME);
System.setProperty("DOCKERHUB_USER_PASSWORD", creds.DOCKERHUB_USER_PASSWORD);

