Bu uygulama, Clean Architecture prensiplerine uygun olarak tasarlanmış bir Firebase Authentication uygulamasıdır. Hilt kullanılarak bağımlılık yönetimi gerçekleştirilmiş ve Use Case katmanı aracılığıyla Repository ve ViewModel arasındaki bağlantılar sağlanmıştır.

Uygulama, ilk açıldığında kullanıcıyı kayıt olma sayfasına yönlendirir. Kayıt olma işlemlerinde olası hatalar ele alınmış ve kullanıcı bilgilendirilmiştir. Kayıt tamamlandıktan sonra kullanıcı ana sayfaya yönlendirilir. Kullanıcı uygulamadan çıkıp tekrar girdiğinde, doğrudan ana sayfadan başlanır.

Uygulamanın tasarımı şu şekilde yapılandırılmıştır:

Hilt: Bağımlılıkları yönetmek amacıyla kullanılır. Hilt, DI (Dependency Injection) sağlayarak kodun test edilebilirliğini ve sürdürülebilirliğini artırır.

Clean Architecture: Uygulama, Clean Architecture prensiplerine uygun olarak katmanlara ayrılmıştır. Bu yaklaşım, veri, iş mantığı ve kullanıcı arayüzü bileşenlerinin bağımsız bir şekilde yönetilmesini sağlar.

Use Case: İş mantığını yöneten ve işlevlerin tek bir sorumluluk doğrultusunda düzenlendiği katmandır. Repository ve ViewModel arasındaki bağlantıyı sağlayarak kodun modülerliğini ve bakımını kolaylaştırır.

ViewModel: Kullanıcı arayüzü ile iş mantığı arasındaki köprüyü kurar ve kullanıcı etkileşimlerine yanıt verir.

Repository: Veritabanı veya uzak veri kaynakları ile etkileşimde bulunur. Firebase Authentication işlemlerini yönetir ve Use Case katmanına veri sağlar. Bağımsız olarak test edilebilir ve iş mantığından ayrıdır.

Bu yapı, kodun okunabilirliğini, bakımını ve test edilebilirliğini artırır. Her bir fonksiyon ve sınıf, belirli bir işlevi yerine getirecek şekilde tasarlanmıştır, bu da uygulamanın daha düzenli ve yönetilebilir olmasını sağlar.
