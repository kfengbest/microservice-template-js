import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

final String id = 'global_usnp_aws_r'
final String description = ''
final String username = System.getProperty('AWS_ACCESS_KEY')
final String password = System.getProperty('AWS_ACCESS_SECRET')

println username

final Credentials credentials = (Credentials) new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL, id, description, username, password
)

SystemCredentialsProvider
    .getInstance()
    .getStore()
    .addCredentials(Domain.global(), credentials)

