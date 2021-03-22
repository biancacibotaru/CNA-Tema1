import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.PersonInfoGrpc;
import proto.Person;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8999).usePlaintext().build();
        PersonInfoGrpc.PersonInfoBlockingStub personStub = PersonInfoGrpc.newBlockingStub(channel);
        Scanner scanner=new Scanner(System.in);
        int option=1;
        System.out.println("The name is: ");
        String name=scanner.next();
        System.out.println("CNP is: ");
        String cnp=scanner.next();

        Person.InfoReply reply = personStub.getPersonInfo(Person.PersonRequest.newBuilder().setName(name).setCnp(cnp).build());

        while(option!=0) {
            System.out.println("If you want to close the client, introduce 0!");
            option = scanner.nextInt();
        }
        channel.shutdown();
        scanner.close();
    }
}