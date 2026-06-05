package ec.edu.puce.githubclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import ec.edu.puce.githubclient.Models.Repository
import ec.edu.puce.githubclient.Models.UpdateRepositoryPayload
import ec.edu.puce.githubclient.services.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoListViewModel : ViewModel() {
    private val _repos = MutableStateFlow<List<Repository>>( value = emptyList())
    val repos: StateFlow<List<Repository>> = _repos.asStateFlow()

    private val _isLoading = MutableStateFlow( value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMsg = MutableStateFlow<String?>( value = null)
    val errMsg: StateFlow<String?> = _errorMsg.asStateFlow()

    init {
        fetchRepos()
    }

    fun fetchRepos () {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            try {
                _repos.value = RetrofitClient.apiService.getRepositories()
            } catch (e: Exception) {
                _errorMsg.value = "Error al cargar repositorios: ${e.localizedMessage}"
                e.printStackTrace()
            }  finally {
                _isLoading.value = false
            }
        }
    }
    fun updateRepository(
        owner: String,
        repo: String,
        newName: String,
        newDescription: String
    ) {

        viewModelScope.launch {

            try {

                _isLoading.value = true

                RetrofitClient.apiService.updateRepository(
                    owner,
                    repo,
                    UpdateRepositoryPayload(
                        name = newName,
                        description = newDescription
                    )
                )

                fetchRepos()

            } catch (e: Exception) {

                _errorMsg.value =
                    "Error al actualizar repositorio: ${e.localizedMessage}"

            } finally {

                _isLoading.value = false

            }

        }

    }
    fun deleteRepository(
        owner: String,
        repo: String
    ) {

        viewModelScope.launch {

            try {

                _isLoading.value = true

                RetrofitClient.apiService.deleteRepository(
                    owner,
                    repo
                )

                fetchRepos()

            } catch (e: Exception) {

                _errorMsg.value =
                    "Error al eliminar repositorio: ${e.localizedMessage}"

            } finally {

                _isLoading.value = false

            }

        }

    }
}