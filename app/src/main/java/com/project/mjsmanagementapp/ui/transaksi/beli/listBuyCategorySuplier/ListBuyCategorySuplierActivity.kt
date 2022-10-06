package com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategorySuplierimport androidx.appcompat.app.AppCompatActivityimport android.os.Bundleimport android.view.Viewimport android.view.inputmethod.EditorInfoimport android.widget.SearchViewimport android.widget.Toastimport androidx.recyclerview.widget.LinearLayoutManagerimport com.project.mjsmanagementapp.Rimport com.project.mjsmanagementapp.adapter.transaksi.beli.listBuyCategory.ListBuyCategoryAdapterimport com.project.mjsmanagementapp.adapter.transaksi.beli.listBuyCategorySuplier.ListBuyCategorySuplierAdapterimport com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategorySuplier.ResponseListTrcBuyCategorySuplierItemimport com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategory.ListBuyCategoryActivityPresenterimport kotlinx.android.synthetic.main.listpopembelian_activity.*import kotlinx.android.synthetic.main.listsuplier_activity.*import kotlinx.android.synthetic.main.listsuplierpembelian_activity.*import kotlinx.android.synthetic.main.listsuplierpembelian_activity.btnimgBackimport kotlinx.android.synthetic.main.listsuplierpembelian_activity.loadingListSuplierimport kotlinx.android.synthetic.main.listsuplierpembelian_activity.txtSuplierTotalimport org.jetbrains.anko.sdk27.coroutines.onClickclass ListBuyCategorySuplierActivity : AppCompatActivity(), ListBuyCategorySuplierActivityContract {    private var listBuySuplierAdapterName : ListBuyCategorySuplierAdapter? = null    private lateinit var presenter: ListBuyCategorySuplierActivityPresenter    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        setContentView(R.layout.listsuplierpembelian_activity)        btnimgBack.onClick {            finish()        }        getListBuyCategorySuplier()    }    private fun getListBuyCategorySuplier() {        presenter = ListBuyCategorySuplierActivityPresenter(this)        presenter.getListBuyCategorySuplier(loadingListSuplier)        presenter.getSearchListBuySuplier(loadingListSuplier)        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL        rvListSuplierBeli1.layoutManager = linearLayoutManager        rvListSuplierBeli1.layoutManager = LinearLayoutManager(this)        rvListSuplierBeli2.layoutManager = LinearLayoutManager(this)    }    override fun onResume() {        super.onResume()        getListBuyCategorySuplier()    }    override fun onPause() {        super.onPause()    }    override fun onSuccessGetListBuySuplier(data: List<ResponseListTrcBuyCategorySuplierItem>?, totalSuplier: Int    ) {        txtSuplierTotal.setText(totalSuplier.toString())        rvListSuplierBeli1.adapter = ListBuyCategorySuplierAdapter(data, object : ListBuyCategorySuplierAdapter.onClickItem{            override fun clicked(item: ResponseListTrcBuyCategorySuplierItem?) {            }        })    }    override fun onErrorGetListBuySuplier(msg: String) {        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()    }    override fun onSuccesGetSearchListBuySuplier(data: List<ResponseListTrcBuyCategorySuplierItem>?) {        listBuySuplierAdapterName = ListBuyCategorySuplierAdapter(data, object : ListBuyCategorySuplierAdapter.onClickItem{            override fun clicked(item: ResponseListTrcBuyCategorySuplierItem?) {            }        })        rvListSuplierBeli1.visibility = View.VISIBLE        svListPembelianSuplier.imeOptions = EditorInfo.IME_ACTION_SEARCH        svListPembelianSuplier.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{            override fun onQueryTextSubmit(query: String?): Boolean = false            override fun onQueryTextChange(action: String?): Boolean {                if (action != null){                    if (action.isEmpty()){                        rvListSuplierBeli1.visibility = View.VISIBLE                        rvListSuplierBeli2.visibility = View.GONE                    }else if(action.length > 0){                        val filterNama = data?.filter {it.trcBuyCategorySupplierName!!.contains("$action", true) }                        listBuySuplierAdapterName = ListBuyCategorySuplierAdapter(filterNama as List<ResponseListTrcBuyCategorySuplierItem>, object : ListBuyCategorySuplierAdapter.onClickItem{                            override fun clicked(item: ResponseListTrcBuyCategorySuplierItem?) {                            }                        })                        if (action.isNotEmpty()){                            rvListSuplierBeli2.visibility = View.VISIBLE                            rvListSuplierBeli2.adapter = listBuySuplierAdapterName                            rvListSuplierBeli1.visibility = View.GONE                        }else{                            rvListSuplierBeli1.visibility = View.VISIBLE                            rvListSuplierBeli2.visibility = View.GONE                        }                    }                }                return false            }        })    }    override fun onFailedGetSearchListBuySuplier(msg: String?) {    }}