using System;
using System.ServiceModel;

namespace WSDL.mensajes
{
    [ServiceContract]
    public interface Mensajes{
        [OperationContract]
        String Saludar(string nombre);
        [OperationContract]
        String Mostrar(int id);
    }
}