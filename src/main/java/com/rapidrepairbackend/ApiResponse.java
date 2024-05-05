package com.rapidrepairbackend;

public class ApiResponse<T> {
  private T data;
  private String message;

  public ApiResponse(T data, String message) {
    this.data = data;
    this.message = message;
  }

  // Getters and setters
  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  // Static factory methods for success and error
  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(data, "Success");
  }

  public static <T> ApiResponse<T> error(String message) {
    return new ApiResponse<>(null, message);
  }
}
