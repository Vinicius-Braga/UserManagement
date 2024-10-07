/* eslint-disable @next/next/no-img-element */
'use client';
import { ResourceService } from '@/service/ResourceService';
import { Projeto } from '@/types';
import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { Dialog } from 'primereact/dialog';
import { FileUpload } from 'primereact/fileupload';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import { Toolbar } from 'primereact/toolbar';
import { classNames } from 'primereact/utils';
import React, { useEffect, useMemo, useRef, useState } from 'react';
 

const Resource = () => {
    let emptyResource: Projeto.Resource = {
        id: 0,
        name: '',
        code: ''
    };

    const [resources, setResources] = useState<Projeto.Resource[]>([]);
    const [resourceDialog, setResourceDialog] = useState(false);
    const [deleteResourceDialog, setDeleteResourceDialog] = useState(false);
    const [deleteResourcesDialog, setDeleteResourcesDialog] = useState(false);
    const [resource, setResource] = useState<Projeto.Resource>(emptyResource);
    const [selectedResources, setSelectedResources] = useState<Projeto.Resource[]>([]);;
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState('');
    const toast = useRef<Toast>(null);
    const dt = useRef<DataTable<any>>(null);
    const resourceService = useMemo(() => new ResourceService(), []);

    useEffect(() => {
        if (resources.length == 0) {
            resourceService.listAll().then((response) => {
                console.log(response.data);
                setResources(response.data);
            }).catch((error) => {
                console.log(error)
            })
        }
    }, [resourceService, resources]);

    const openNew = () => {
        setResource(emptyResource);
        setSubmitted(false);
        setResourceDialog(true);
    };

    const hideDialog = () => {
        setSubmitted(false);
        setResourceDialog(false);
    };

    const hideDeleteResourceDialog = () => {
        setDeleteResourceDialog(false);
    };

    const hideDeleteResourcesDialog = () => {
        setDeleteResourcesDialog(false);
    };

    const saveResource = () => {
        setSubmitted(true);

        if(!resource.id) {
            resourceService.create(resource)
            .then((response) => {
                setResourceDialog(false);
                setResource(emptyResource);
                setResources([]);
                toast.current?.show({
                    severity: 'info',
                    summary: 'Sucesso',
                    detail: 'Usuário cadastrado com sucesso!'
                })
            }).catch((error) => {
                console.log(error.data.message);
                toast.current?.show({
                    severity: 'error',
                    summary: 'Sucesso',
                    detail: 'Error ao cadastrar' + error.data.message
                })
            });
        }else {
            resourceService.alter(resource)
            .then((response) => {
                setResourceDialog(false);
                setResource(emptyResource);
                setResources([]);
                toast.current?.show({
                    severity: 'info',
                    summary: 'Sucesso',
                    detail: 'Usuário alterado com sucesso!'
                })
            }).catch((error) => {
                console.log(error.data.message);
                toast.current?.show({
                    severity: 'error',
                    summary: 'Sucesso',
                    detail: 'Error ao alterar' + error.data.message
                })
            });
        }
    };

    const editResource = (resource: Projeto.Resource) => {
        setResource({ ...resource });
        setResourceDialog(true);
    };

    const confirmDeleteResource = (resource: Projeto.Resource) => {
        setResource(resource);
        setDeleteResourceDialog(true);
    };

    const deleteResource = () => {
        if(resource.id) {

            resourceService.delete(resource.id)
            .then((response) => {
                setResource(emptyResource);
                setDeleteResourceDialog(false);
                setResources([]);
                toast.current?.show({
                        severity: 'success',
                        summary: 'Sucesso!',
                        detail: 'Usuario deletado com sucesso!',
                        life: 3000
                    });
            }).catch((error) => {
                toast.current?.show({
                        severity: 'error',
                        summary: 'Erro!',
                        detail: 'Erro ao deletar o usuario!',
                        life: 3000
                    });
            });
        }
    };

   

    const exportCSV = () => {
        dt.current?.exportCSV();
    };

    const confirmDeleteSelected = () => {
        setDeleteResourcesDialog(true);
    };

    const deleteSelectedResources = () => {
        Promise.all (selectedResources.map(async (_resource) => {
            if(_resource.id) {
                await resourceService.delete(_resource.id)
            }
            
        })).then((response) => {
            setResources([]);
            setSelectedResources([])
            setDeleteResourcesDialog(false);
            toast.current?.show({
                severity: 'success',
                summary: 'Sucesso!',
                detail: 'Usuarios deletados com sucesso!',
                life: 3000
            });
        }).catch((error) => {
            toast.current?.show({
                severity: 'error',
                summary: 'Erro!',
                detail: 'Erro ao deletar o usuarios!',
                life: 3000
            });
        });
    }

    const onInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, name: string) => {
        const val = (e.target && e.target.value) || '';
        let _resource = { ...resource };
        _resource[`${name}`] = val;

        setResource(_resource);
    };

    const leftToolbarTemplate = () => {
        return (
            <React.Fragment>
                <div className="my-2">
                    <Button label="Novo" icon="pi pi-plus" severity="success" className=" mr-2" onClick={openNew} />
                    <Button label="Excluir" icon="pi pi-trash" severity="danger" onClick={confirmDeleteSelected} disabled={!selectedResources || !(selectedResources as any).length} />
                </div>
            </React.Fragment>
        );
    };

    const rightToolbarTemplate = () => {
        return (
            <React.Fragment>
                <FileUpload mode="basic" accept="image/*" maxFileSize={1000000} chooseLabel="Import" className="mr-2 inline-block" />
                <Button label="Exportar" icon="pi pi-upload" severity="help" onClick={exportCSV} />
            </React.Fragment>
        );
    };

    const idBodyTemplate = (rowData: Projeto.Resource) => {
        return (
            <>
                <span className="p-column-title">Código</span>
                {rowData.id}
            </>
        );
    };

    const nameBodyTemplate = (rowData: Projeto.Resource) => {
        return (
            <>
                <span className="p-column-title">Nome</span>
                {rowData.name}
            </>
        );
    };

    const keyBodyTemplate = (rowData: Projeto.Resource) => {
      return (
          <>
              <span className="p-column-title">Chave</span>
              {rowData.code}
          </>
      );
  };


    const actionBodyTemplate = (rowData: Projeto.Resource) => {
        return (
            <>
                <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={() => editResource(rowData)} />
                <Button icon="pi pi-trash" rounded severity="warning" onClick={() => confirmDeleteResource(rowData)} />
            </>
        );
    };

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Gerenciamento de Usuarios</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Search..." />
            </span>
        </div>
    );

    const resourceDialogFooter = (
        <>
            <Button label="Cancelar" icon="pi pi-times" text onClick={hideDialog} />
            <Button label="Salvar" icon="pi pi-check" text onClick={saveResource} />
        </>
    );
    const deleteResourceDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" text onClick={hideDeleteResourceDialog} />
            <Button label="Sim" icon="pi pi-check" text onClick={deleteResource} />
        </>
    );
    const deleteResourcesDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" text onClick={hideDeleteResourcesDialog} />
            <Button label="Sim" icon="pi pi-check" text onClick={deleteSelectedResources} />
        </>
    );

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <div className="card">
                    <Toast ref={toast} />
                    <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>

                    <DataTable
                        ref={dt}
                        value={resources}
                        selection={selectedResources}
                        onSelectionChange={(e) => setSelectedResources(e.value as any)}
                        dataKey="id"
                        paginator
                        rows={10}
                        rowsPerPageOptions={[5, 10, 25]}
                        className="datatable-responsive"
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                        currentPageReportTemplate="Mostrando {first} até {last} de {totalRecords} resources"
                        globalFilter={globalFilter}
                        emptyMessage="Nenhum usuario encontrado :("
                        header={header}
                        responsiveLayout="scroll"
                    >
                        <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}></Column>
                        <Column field="code" header="Código" sortable body={idBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
                        <Column field="name" header="Nome" sortable body={nameBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
                        <Column field="code" header="Chave" sortable body={keyBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
                        <Column body={actionBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
                    </DataTable>

                    <Dialog visible={resourceDialog} style={{ width: '450px' }} header="Detalhes do Usuário" modal className="p-fluid" footer={resourceDialogFooter} onHide={hideDialog}>
                        <div className="field">
                            <label htmlFor="name">Nome</label>
                            <InputText
                                id="name"
                                value={resource.name}
                                onChange={(e) => onInputChange(e, 'name')}
                                required
                                autoFocus
                                className={classNames({
                                    'p-invalid': submitted && !resource.name
                                })}
                            />
                            {submitted && !resource.name && <small className="p-invalid">Nome é obrigatorio.</small>}
                        </div>

                        <div className="field">
                            <label htmlFor="code">Chave</label>
                            <InputText
                                id="code"
                                value={resource.code}
                                onChange={(e) => onInputChange(e, 'code')}
                                required
                                autoFocus
                                className={classNames({
                                    'p-invalid': submitted && !resource.code
                                })}
                            />
                            {submitted && !resource.code && <small className="p-invalid">Chave é obrigatorio.</small>}
                        </div>

                    </Dialog>

                    <Dialog visible={deleteResourceDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteResourceDialogFooter} onHide={hideDeleteResourceDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {resource && (
                                <span>
                                    Você realmente deseja excluir o usuario <b>{resource.name}</b>?
                                </span>
                            )}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteResourcesDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteResourcesDialogFooter} onHide={hideDeleteResourcesDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {resource && <span>Você realmente deseja excluir os usuarios selecionados?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};

export default Resource;
