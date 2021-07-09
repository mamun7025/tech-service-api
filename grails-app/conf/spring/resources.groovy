import auth.DefaultJsonPayloadCredentialsExtractor
import auth.MyLoginEventListener
import auth.MyLoginTokenEventListener
import auth.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    myLoginEventListener(MyLoginEventListener) // web
    myLoginTokenEventListener(MyLoginTokenEventListener) // token
    credentialsExtractor(DefaultJsonPayloadCredentialsExtractor)
}
