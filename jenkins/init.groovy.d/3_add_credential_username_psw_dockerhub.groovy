import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

final String id = 'dockerHub'
final String description = ''
final String username = System.getProperty('DOCKERHUB_USER_NAME')
final String password = System.getProperty('DOCKERHUB_USER_PASSWORD')

println username
println password

final Credentials credentials = (Credentials) new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL, id, description, username, password
)

SystemCredentialsProvider
    .getInstance()
    .getStore()
    .addCredentials(Domain.global(), credentials)

