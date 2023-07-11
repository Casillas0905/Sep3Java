package com.ServerSep3.Server.GrpcImpl;

import GrpcClasses.Post.Post;
import GrpcClasses.Post.PostGrpcGrpc;
import GrpcClasses.User.User;
import com.ServerSep3.Server.Model.PostModel;
import com.ServerSep3.Server.Model.SearchParameters;
import com.ServerSep3.Server.Model.UserModel;
import com.ServerSep3.Server.Service.PostService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@GRpcService
public class PostGrpcImpl extends PostGrpcGrpc.PostGrpcImplBase {

    @Autowired
    PostService postService;

    public PostGrpcImpl() {
    }

    @Override
    public void createPost(Post.PostModelGrpc request, StreamObserver<Post.Empty> responseObserver) {
        PostModel postModel= new PostModel(request.getId(),
                request.getUserId(),
                request.getCategory(),
                request.getTitle(),
                request.getDescription(),
                request.getImageUrl(),
                request.getLocation());
        postService.createPost(postModel);
        Post.Empty empty = Post.Empty.newBuilder().build();
        responseObserver.onNext(empty);
        responseObserver.onCompleted();
    }

    @Override
    public void findById(Post.GetById request, StreamObserver<Post.PostModelGrpc> responseObserver) {
        PostModel model=postService.findById(request.getId());
        if (model == null){
            System.out.println("its null");
            Post.PostModelGrpc response= Post.PostModelGrpc.newBuilder()
                    .setCategory("niull")
                    .setId(0)
                    .setDescription("niull")
                    .setImageUrl("niull")
                    .setUserId(0)
                    .setLocation("niull")
                    .setTitle("niull")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        else {
            Post.PostModelGrpc response= Post.PostModelGrpc.newBuilder()
                    .setCategory(model.getCategory())
                    .setId(model.getId())
                    .setDescription(model.getDescription())
                    .setImageUrl(model.getImageUrl())
                    .setUserId(model.getUserId())
                    .setLocation(model.getLocation())
                    .setTitle(model.getTitle())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void updatePost(Post.PostModelGrpc request, StreamObserver<Post.Empty> responseObserver) {
        super.updatePost(request, responseObserver);
    }

    @Override
    public void deletePost(Post.GetById request, StreamObserver<Post.Empty> responseObserver) {
        super.deletePost(request, responseObserver);
    }

    @Override
    public void findByParameters(Post.SearchParameters request, StreamObserver<Post.PostModelGrpc> responseObserver) {
        SearchParameters parameters=new SearchParameters(request.getTitle(), request.getLocation(), request.getCategory());
        if(parameters.getTitle().equals("niull")){
            parameters.setTitle(null);
        }
        if(parameters.getLocation().equals("niull")){
            parameters.setLocation(null);
        }
        if(parameters.getCategory().equals("niull")){
            parameters.setCategory(null);
        }
        List<PostModel> list= postService.findByParameters(parameters);
        List<Post.PostModelGrpc> listGrpc= new ArrayList<>();
        for (int i=0;i< list.size();i++){
            Post.PostModelGrpc post= Post.PostModelGrpc.newBuilder()
                    .setCategory(list.get(i).getCategory())
                    .setId(list.get(i).getId())
                    .setDescription(list.get(i).getDescription())
                    .setImageUrl(list.get(i).getImageUrl())
                    .setUserId(list.get(i).getUserId())
                    .setLocation(list.get(i).getLocation())
                    .setTitle(list.get(i).getTitle())
                    .build();
            listGrpc.add(post);
        }
        for(Post.PostModelGrpc postModelGrpc : listGrpc){
            responseObserver.onNext(postModelGrpc);
        }
        responseObserver.onCompleted();
    }
    @Override
    public void findByUserId(Post.GetById request, StreamObserver<Post.PostModelGrpc> responseObserver) {
        List<PostModel> list= postService.findByUserId(request.getId());
        List<Post.PostModelGrpc> listGrpc= new ArrayList<>();
        for (int i=0;i< list.size();i++){
            Post.PostModelGrpc post= Post.PostModelGrpc.newBuilder()
                    .setCategory(list.get(i).getCategory())
                    .setId(list.get(i).getId())
                    .setDescription(list.get(i).getDescription())
                    .setImageUrl(list.get(i).getImageUrl())
                    .setUserId(list.get(i).getUserId())
                    .setLocation(list.get(i).getLocation())
                    .setTitle(list.get(i).getTitle())
                    .build();
            listGrpc.add(post);
        }
        for(Post.PostModelGrpc postModelGrpc : listGrpc){
            responseObserver.onNext(postModelGrpc);
        }
        responseObserver.onCompleted();
    }
}
