package ec.edu.puce.githubclient.Models

data class UpdateRepositoryPayload(
    val name: String,
    val description: String?
)